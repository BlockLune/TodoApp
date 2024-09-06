import Header from "../components/Header";
import SideMenu from "../components/SideMenu";
import TodoItem from "../components/TodoItem";

export default function App() {
  return (
    <div className="flex flex-col h-screen">
      <Header />
      <div className="flex flex-1 overflow-hidden">
        <SideMenu />
        <main className="flex-1 p-4 overflow-y-auto">
          <TodoItem text="Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, hic?" />
          <TodoItem text="Lorem ipsum dolor sit amet consectetur adipisicing." />
        </main>
      </div>
    </div>
  );
}
