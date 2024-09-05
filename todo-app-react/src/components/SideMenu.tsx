import TodoList from "./TodoList";

export default function SideMenu() {
    return (
        <nav className="w-64 bg-slate-100 border-r border-slate-200 overflow-y-auto p-4">
            <ul className="flex flex-col">
                <li><TodoList numTodos={10}>Today</TodoList></li>
                <li><TodoList active>All</TodoList></li>
                <li><TodoList>My Custom List 1</TodoList></li>
                <li><TodoList>My Custom List 2</TodoList></li>
                <li><TodoList>My Custom List 3</TodoList></li>
            </ul>
        </nav>
    );
}