const QuestionForm = ({ onSave, disabled, onCancel }) => {
    const onSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const entries = [...formData.entries()];

        const company = entries.reduce((acc, entry) => {
            const [k, v] = entry;
            acc[k] = v;
            return acc;
        }, {});

        return onSave(company);
    };

    return (
        <form className="CompanyForm" onSubmit={onSubmit}>

            <div className="control">
                <label htmlFor="name">Name:</label>
                <input
                    name="name"
                    id="name"
                />
            </div>

            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    Create company
                </button>

                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default QuestionForm;
