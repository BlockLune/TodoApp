import CheckBoxOutlineBlankIcon from "@mui/icons-material/CheckBoxOutlineBlank";
import CheckBoxIcon from "@mui/icons-material/CheckBox";

import { useState } from "react";

export default function Check() {
  const [isChecked, setIsChecked] = useState(false);
  const icon = isChecked ? (
    <CheckBoxIcon onClick={() => setIsChecked(false)} />
  ) : (
    <CheckBoxOutlineBlankIcon onClick={() => setIsChecked(true)} />
  );
  const colorClasses = isChecked ? "text-green-600" : "text-slate-400";
  return (
    <div
      className={`rounded-full p-2 ${colorClasses} hover:bg-slate-100 hover:text-green-600`}
    >
      {icon}
    </div>
  );
}
