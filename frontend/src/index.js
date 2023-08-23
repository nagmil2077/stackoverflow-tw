import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import ErrorPage from "./Components/Pages/ErrorPage";

import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Layout from "./Components/Pages/Layout";
import MainPage from "./Components/Pages/MainPage";
import QuestionCreator from "./Components/Pages/QuestionCreator";
import QuestionUpdater from "./Components/Pages/QuestionUpdater";
import AnswerTable from "./Components/AnswerTable";
import AnswerUpdater from "./Components/Pages/AnswerUpdater";
import AnswerCreator from "./Components/Pages/AnswerCreator";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout/>,
        errorElement: <ErrorPage/>,
        children: [
            {
                path: "/",
                element: <MainPage/>,
            },
            {
                path: "/create",
                element: <QuestionCreator />,
            },
            {
                path: "/update/:id",
                element: <QuestionUpdater />,
            },
            {
                path: "/question/:id",
                element: <AnswerTable />,
            },
            {
                path: "/question/:id/update/:id",
                element: <AnswerUpdater />,
            },
            {
                path: "/question/:id/create",
                element: <AnswerCreator />,
            }
        ],
    },
]);


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
