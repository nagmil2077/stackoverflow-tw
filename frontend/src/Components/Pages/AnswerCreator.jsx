import {useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import AnswerForm from "../AnswerForm";

const createAnswer = (id, answer) => {
    return fetch(`/answers/${id}/create`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(answer),
    }).then((res) => {
        return res.json()
    });
};

const AnswerCreator = () => {
    const navigate = useNavigate();
    const {questionId} = useParams();
    const [loading, setLoading] = useState(false);

    const handleCreateAnswer = (answer) => {
        setLoading(true);

        createAnswer(questionId, answer)
            .then(() => {
                setLoading(false);
                navigate(`/question/${questionId}`);
            })
    };

    return (
        <AnswerForm
            onSave={handleCreateAnswer}
            disabled={loading}
            onCancel={() => navigate(`/question/${questionId}`)}
        />
    );
};

export default AnswerCreator;
