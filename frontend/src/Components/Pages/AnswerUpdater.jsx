import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import Loading from "../Loading";
import AnswerForm from "../AnswerForm";

const updateAnswer = (answer) => {
    console.log("updateAnswer(): " + answer.description)
    return fetch(`/questions/update/${answer.id}`, {
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
    console.log(id)
    return fetch(`/answers/${id}`).then((res) => {
        console.log(res)
        return res.json()});
};

const AnswerUpdater = () => {
    const {id} = useParams();
    const navigate = useNavigate();

    const [answer, setAnswer] = useState(null);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [answerLoading, setAnswerLoading] = useState(true);


    useEffect(() => {
        setAnswerLoading(true);
        fetchAnswer(id)
            .then((answer) => {
                setAnswer(answer);
                setAnswerLoading(false);
            })
    }, [id]);

    const handleUpdateAnswer = (answer) => {
        setUpdateLoading(true);
        updateAnswer(answer)
            .then(() => {
                setUpdateLoading(false);
                navigate(`/question/${id}`);
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
            onCancel={() => navigate(`/question/${id}`)}
        />
    );
};

export default AnswerUpdater;
