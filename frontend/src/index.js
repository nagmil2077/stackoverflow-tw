import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import ErrorPage from "./Components/Pages/ErrorPage";

import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Layout from "./Components/Pages/Layout";
import MainPage from "./Components/Pages/MainPage";
import QuestionCreator from "./Components/Pages/QuestionCreator";

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