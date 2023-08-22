import { useEffect, useState } from "react";
import Loading from "../Loading";
import QuestionTable from "../QuestionTable";

const fetchQuestions = () => {
    return fetch("/questions/all").then((res) => res.json());
};

const deleteQuestion = (id) => {
    return fetch(`/questions/${id}`, { method: "DELETE" }).then((res) =>
        res.json());
};

const MainPage = () => {
    const [loading, setLoading] = useState(true);
    const [questions, setQuestions] = useState(null);

    const handleDelete = (id) => {
        deleteQuestion(id);

        setQuestions((questions) => {
            return questions.filter((question) => question.id !== id);
        });
    };

    useEffect(() => {
        fetchQuestions()
            .then((questions) => {
                setLoading(false);
                setQuestions(questions);
            })
    }, []);

    if (loading) {
        return <Loading />;
    }

    return <QuestionTable questions={questions} onDelete={handleDelete} />;
};

export default MainPage;
