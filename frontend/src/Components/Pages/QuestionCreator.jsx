import { useState } from "react";
import { useNavigate } from "react-router-dom";
import QuestionForm from "../QuestionForm";

const createQuestion = (question) => {
    return fetch("/questions/", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(question),
    }).then((res) => res.json());
};

const QuestionCreator = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);

    const handleCreateCompany = (company) => {
        setLoading(true);

        createQuestion(company)
            .then(() => {
                setLoading(false);
                navigate("/");
            })
    };

    return (
        <QuestionForm
            onCancel={() => navigate("/")}
            disabled={loading}
            onSave={handleCreateCompany}
        />
    );
};

export default QuestionCreator;
