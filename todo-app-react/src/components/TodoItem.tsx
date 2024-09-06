import Check from "./Check";
import Edit from "./Edit";
import Star from "./Star";
import TodoText from "./TodoText";

type TodoItemProps = {
  text: string;
};

export default function TodoItem({ text }: TodoItemProps) {
  return (
    <div className="w-full h-14 rounded-full p-2 flex flex-1 items-center justify-between transition hover:bg-slate-50">
      <div className="flex items-center w-full">
        <Check />
        <TodoText initialText={text} />
      </div>
      <div className="flex items-center">
        <Edit />
        <Star />
      </div>
    </div>
  );
}
