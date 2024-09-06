import { useState } from "react";

type TodoTextProps = {
  initialText: string;
};

export default function TodoText({ initialText }: TodoTextProps) {
  const [text, setText] = useState(initialText);

  return (
    <input
      className="rounded-full bg-inherit hover:bg-slate-100 py-2 px-4 appearance-none outline-none w-full"
      value={text}
      onChange={(event) => setText(event.target.value)}
    />
  );
}
