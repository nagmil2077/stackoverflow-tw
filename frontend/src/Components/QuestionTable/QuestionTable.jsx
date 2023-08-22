import { Link } from "react-router-dom";
import "./QuestionTable.css";

const QuestionTable = ({ questions, onDelete }) => (
    <div className="QuestionTable">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Timestamp</th>
                <th />
            </tr>
            </thead>
            <tbody>
            {questions.map((question) => (
                <tr key={question.id}>
                    <td>{question.id}</td>
                    <td>{question.title}</td>
                    <td>{question.description}</td>
                    <td>{question.timestamp}</td>
                    <td>
                        <Link to={`/update/${question.id}`}>
                            <button type="button">Update</button>
                        </Link>
                        <button type="button" onClick={() => onDelete(question.id)}>
                            Delete
                        </button>
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    </div>
);

export default QuestionTable;
