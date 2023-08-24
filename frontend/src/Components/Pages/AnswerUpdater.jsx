import {useEffect, useState, } from "react";
import {useNavigate, useParams, useLocation} from "react-router-dom";
import Loading from "../Loading";
import AnswerForm from "../AnswerForm";

const updateAnswer = (answer) => {
    return fetch(`/answers/${answer.id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(answer),
    }).then((res) => {
        return res.json()
    });
};

const fetchAnswer = (id) => {
    return fetch(`/answers/${id}`).then((res) => {
        return res.json()});
};

const AnswerUpdater = () => {
    const {answerId, questionId} = useParams();
    const navigate = useNavigate();
    const path = useLocation();

    const [answer, setAnswer] = useState(null);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [answerLoading, setAnswerLoading] = useState(true);


    useEffect(() => {
        setAnswerLoading(true);
        fetchAnswer(answerId)
            .then((answer) => {
                setAnswer(answer);
                setAnswerLoading(false);
            })
    }, [answerId, questionId]);

    const handleUpdateAnswer = (answer) => {
        setUpdateLoading(true);
        updateAnswer(answer)
            .then(() => {
                setUpdateLoading(false);
                navigate(`/question/${questionId}`);
            });
    };

    if (answerLoading) {
        return <Loading/>;
    }

    return (
        <AnswerForm
            answer={answer}
            onSave={handleUpdateAnswer}
            disabled={updateLoading}
            onCancel={() => navigate(`/question/${questionId}`)}
        />
    );
};

export default AnswerUpdater;
