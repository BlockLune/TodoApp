import { useAuth } from "@/utils/auth";
import { Navigate } from "react-router-dom";

export default function AuthenticatedRoute({ children }) {
  const authContext = useAuth();
  if (authContext.isAuthenticated) {
    return children;
  }
  return <Navigate to="/login" />;
}
