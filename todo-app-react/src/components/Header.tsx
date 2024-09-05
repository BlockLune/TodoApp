import { Avatar, IconButton } from "@mui/material";
import Button from "@mui/material/Button";

export default function Header() {
  return (
    <div className="flex justify-between items-center bg-slate-100 h-16 p-4 border border-slate-200">
      <Button>Todo App</Button>
      <ul className="flex gap-4 items-center">
        <li>
          <Button>Settings</Button>
        </li>
        <li>
          <IconButton>
            <Avatar>U</Avatar>
          </IconButton>
        </li>
      </ul>
    </div>
  );
}
