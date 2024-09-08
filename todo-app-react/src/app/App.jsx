import {
  createBrowserRouter,
  Navigate,
  RouterProvider,
} from "react-router-dom";

import LoginPage from "./LoginPage";
import TodoPage from "./TodoPage";

const router = createBrowserRouter([
  {
    path: "/",
    children: [
      { index: true, element: <Navigate to="/login" /> },
      { path: "login", element: <LoginPage /> },
    ],
  },
]);

export default function App() {
  return (
    <RouterProvider router={router}>
      <TodoPage />
    </RouterProvider>
  );
}
