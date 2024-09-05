import Header from "../components/Header";
import SideMenu from "../components/SideMenu";

export default function App() {
  return <div className="flex flex-col h-screen">
    <Header />
    <div className="flex flex-1 overflow-hidden">
      <SideMenu />
      <main className="flex-1 p-4 overflow-y-auto">
        Main Content
      </main>
    </div>
  </div>;
}
