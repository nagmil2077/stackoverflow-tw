import {useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import AnswerForm from "../AnswerForm";

const createAnswer = (answer) => {
    return fetch("/questions/", {
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
    const {id} = useParams();
    const [loading, setLoading] = useState(false);

    const handleCreateAnswer = (answer) => {
        setLoading(true);

        createAnswer(answer)
            .then(() => {
                setLoading(false);
                navigate(`/question/${id}`);
            })
    };

    return (
        <AnswerForm
            onSave={handleCreateAnswer}
            disabled={loading}
            onCancel={() => navigate(`/question/${id}`)}
        />
    );
};

export default AnswerCreator;
