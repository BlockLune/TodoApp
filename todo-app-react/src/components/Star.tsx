import StarBorderIcon from "@mui/icons-material/StarBorder";
import StarIcon from "@mui/icons-material/Star";

import { useState } from "react";

export default function Star() {
  const [isStarred, setIsStarred] = useState(false);
  const icon = isStarred ? (
    <StarIcon onClick={() => setIsStarred(false)} />
  ) : (
    <StarBorderIcon onClick={() => setIsStarred(true)} />
  );
  const colorClasses = isStarred ? "text-yellow-400" : "text-slate-400";
  return (
    <div
      className={`rounded-full p-2 ${colorClasses} hover:bg-slate-100 hover:text-yellow-400`}
    >
      {icon}
    </div>
  );
}
