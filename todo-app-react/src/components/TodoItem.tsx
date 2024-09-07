import Check from "./Check";
import Edit from "./Edit";
import Star from "./Star";

type TodoItemProps = {
  text: string;
};

export default function TodoItem({ text }: TodoItemProps) {
  return (
    <div className="w-full h-14 rounded-full p-2 flex flex-1 items-center justify-between transition hover:bg-slate-50 hover:shadow hover:cursor-pointer">
      <div className="flex items-center w-full">
        <Check />
        <p className="p-2">{text}</p>
      </div>
      <div className="flex items-center">
        <Edit />
        <Star />
      </div>
    </div>
  );
}
