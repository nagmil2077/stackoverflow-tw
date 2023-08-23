import {Link, Outlet, useParams, useLocation} from "react-router-dom";

import "./Layout.css";

const Layout = () => {
    const location = useLocation();
    const params = useParams();

    if (location.pathname === `/question/${params.id}`) {
        return <div className="Layout">
            <nav>
                <ul>
                    <li className="grow">
                        <Link to="/">Questions</Link>
                    </li>
                    <li>
                        <Link to="/create/answer">
                            <button type="button">Create Answer</button>
                        </Link>
                        <Link to="/create">
                            <button type="button">Create Question</button>
                        </Link>
                    </li>
                </ul>
            </nav>
            <Outlet/>
        </div>
    } else {
        return <div className="Layout">
            <nav>
                <ul>
                    <li className="grow">
                        <Link to="/">Questions</Link>
                    </li>
                    <li>
                        <Link to="/create">
                            <button type="button">Create Question</button>
                        </Link>
                    </li>
                </ul>
            </nav>
            <Outlet/>
        </div>
    }
};

export default Layout;
