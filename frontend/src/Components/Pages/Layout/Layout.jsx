import { Outlet, Link } from "react-router-dom";

import "./Layout.css";

const Layout = () => (
    <div className="Layout">
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
        <Outlet />
    </div>
);

export default Layout;
