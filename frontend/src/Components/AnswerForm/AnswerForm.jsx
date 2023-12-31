const AnswerForm = ({answer, onSave, disabled, onCancel}) => {

    const onSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const entries = [...formData.entries()];

        const answer = entries.reduce((acc, entry) => {
            const [k, v] = entry;
            acc[k] = v;
            return acc;
        }, {});

        return onSave(answer);
    };

    return (
        <form className="AnswerForm" onSubmit={onSubmit}>
            {answer && (
                <input type="hidden" name="id" defaultValue={answer.id}/>
            )}

            <div className="control">
                <label htmlFor="description">Answer:</label>
                <input
                    name="description"
                    id="description"
                    defaultValue={answer ? answer.description : ""}
                />
            </div>

            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    {answer ? "Edit Answer" : "Send Answer"}
                </button>

                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default AnswerForm;
