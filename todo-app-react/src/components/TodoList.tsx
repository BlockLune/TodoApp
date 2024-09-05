type TodoListProps = {
    active?: boolean;
    children: React.ReactNode;
    numTodos?: number;
};

export default function TodoList({ active, children, numTodos }: TodoListProps) {
    const baseClasses = 'bg-inherit rounded px-4 py-2 cursor-pointer transition-all';
    const activeClasses = active ? 'bg-slate-200 hover:bg-slate-300' : 'hover:bg-slate-200';

    return (
        <div className={`${baseClasses} ${activeClasses}`}>
            <div className="flex items-center justify-between">
                <div className={active ? 'font-bold' : ''}>{children}</div>
                <span className="text-sm text-slate-400">{numTodos}</span>
            </div>
        </div>
    );
}