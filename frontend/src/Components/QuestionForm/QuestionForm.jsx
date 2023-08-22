const QuestionForm = ({ onSave, disabled, onCancel }) => {
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

            <div className="control">
                <label htmlFor="title">Title:</label>
                <input
                    name="title"
                    id="title"
                />
                <label htmlFor="description">Description:</label>
                <input
                    name="description"
                    id="description"
                />
            </div>

            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    Create question
                </button>

                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default QuestionForm;
