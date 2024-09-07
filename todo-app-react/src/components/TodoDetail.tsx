import TodoDetailItem from "./TodoDetailItem";
import DeleteIcon from '@mui/icons-material/Delete';

export default function TodoDetail() {
    return (
        <div className="w-96 bg-slate-100 border-l border-slate-200 overflow-y-auto p-4 flex flex-col justify-between">
            <ul className="flex flex-col">
                <li><TodoDetailItem text="test" /></li>
            </ul>
            <div className="flex justify-between p-2 text-slate-400">
                <p>Created at xxx</p>
                <DeleteIcon />
            </div>
        </div>
    );
}