import {Link} from "react-router-dom";
import "./QuestionTable.css";


const QuestionTable = ({questions, onDelete}) => {
    return <div className="QuestionTable">
        <table>
            <thead>
            <tr>
                {/*<th>ID</th>*/}
                <th>Topic</th>
                <th>Question</th>
                <th>Date and time</th>
                <th/>
            </tr>
            </thead>
            <tbody>
            {questions.map((question) => {
                    return <tr key={question.id}>
                        {/*<td>{question.id}</td>*/}
                        <td><Link to={`/question/${question.id}`}><b>{question.title}</b></Link></td>
                        <td><Link to={`/question/${question.id}`}>{question.description}</Link></td>
                        <td>{question.created.substring(0, 16).replaceAll("T", " ")}</td>
                        <td>
                            <Link to={`/update/${question.id}`}>
                                <button type="button">Edit</button>
                            </Link>
                            <button type="button" onClick={() => onDelete(question.id)}>
                                Delete
                            </button>
                        </td>
                    </tr>
                }
            )}
            </tbody>
        </table>
    </div>
};

export default QuestionTable;
