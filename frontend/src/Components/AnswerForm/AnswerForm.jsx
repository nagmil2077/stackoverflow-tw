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

    console.log("AnswerForm: " + answer.description)
    return (
        <form className="AnswerForm" onSubmit={onSubmit}>
            {answer && (
                <input type="hidden" name="id" defaultValue={answer.id}/>
            )}

            <div className="control">
                <label htmlFor="description">Description:</label>
                <input
                    name="description"
                    id="description"
                    defaultValue={answer.description}
                />
            </div>

            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    {answer ? "Update Answer" : "Create Answer"}
                </button>

                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default AnswerForm;
