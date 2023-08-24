const QuestionForm = ({ question, onSave, disabled, onCancel }) => {
    const onSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const entries = [...formData.entries()];

        const question = entries.reduce((acc, entry) => {
            const [k, v] = entry;
            acc[k] = v;
            return acc;
        }, {});

        return onSave(question);
    };

    return (
        <form className="QuestionForm" onSubmit={onSubmit}>
            {question && (
                <input type="hidden" name="id" defaultValue={question.id} />
            )}

            <div className="control">
                <label htmlFor="title">Title:</label>
                <input
                    name="title"
                    id="title"
                    defaultValue={question ? question.title : ''}
                />
                <label htmlFor="description">Description:</label>
                <input
                    name="description"
                    id="description"
                    defaultValue={question ? question.description : ''}
                />
            </div>

            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    {question ? "Update Question" : "Create Question"}
                </button>

                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default QuestionForm;
