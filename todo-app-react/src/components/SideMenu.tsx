import { Divider } from "@mui/material";

export default function SideMenu() {
    return (
        <nav className="w-64 bg-slate-100 border-r border-slate-200 overflow-y-auto p-4">
            <ul className="flex flex-col gap-2">
                <li>TODAY</li>
                <li>ALL</li>
                <Divider />
                <li>TODO LIST 1</li>
                <li>TODO LIST 2</li>
                <li>TODO LIST 3</li>
            </ul>
        </nav>
    );
}