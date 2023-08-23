import {Link, useParams} from "react-router-dom";
import "./AnswerTable.css";
import {useEffect, useState} from "react";
import Loading from "../Loading";

const fetchQuestion = (id) => {
    return fetch(`/questions/${id}`).then((res) => res.json());
};

const fetchAnswers = (id) => {
    console.log("FRONTEND ID: " + id)
    return fetch(`/answers/${id}`).then((res) => res.json());
};

const deleteAnswer = (id) => {
    return fetch(`/questions/${id}`, {method: "DELETE"}).then((res) =>
        res.json());
};

const AnswerTable = () => {
    const {id} = useParams();

    const [question, setQuestion] = useState(null);
    const [questionLoading, setQuestionLoading] = useState(true);
    const [answerLoading, setAnswerLoading] = useState(true);
    const [answers, setAnswers] = useState([]);

    useEffect(() => {
        setQuestionLoading(true);
        fetchQuestion(id)
            .then((question) => {
                setQuestion(question);
                setQuestionLoading(false);
            })
            .then(() => {
                fetchAnswers(id)
                    .then((answers) => {
                        console.log(answers)
                        setAnswers(answers);
                        setAnswerLoading(false);
                    })
            })
    }, [id]);

    const handleDelete = (id) => {
        deleteAnswer(id);

        setAnswers((answers) => {
            return answers.filter((answer) => answer.id !== id);
        });
    };

    if (questionLoading && answerLoading) {
        return <Loading/>;
    }

    return (<>
        <div><b>QUESTION: {question.description}</b></div>
        <div className="AnswerTable">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Timestamp</th>
                    <th/>
                </tr>
                </thead>
                <tbody>
                {answers.map((answer) => {
                        return <tr key={answer.id}>
                            <td>{answer.id}</td>
                            <td>{answer.description}</td>
                            <td>{answer.created}</td>
                            <td>
                                <Link to={`question/update/${answer.id}`}>
                                    <button type="button">Update</button>
                                </Link>
                                <button type="button" onClick={() => handleDelete(answer.id)}>
                                    Delete
                                </button>
                            </td>
                        </tr>
                    }
                )}
                </tbody>
            </table>
        </div>
    </>)
};

export default AnswerTable;
