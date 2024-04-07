import Logo from "./logo";
import SidebarGreeting from './sidebarGreeting';
import SidebarNavigation from "./sidebarNavigation";
import SidebarPersonalNavigation from "./sidebarPersonalNavigation";
import SidebarSpace from './sidebarSpace';

export default function Sidebar() {
  return (
  <div className="hidden lg:fixed lg:inset-y-0 lg:z-50 lg:flex lg:w-72 lg:flex-col">
    <div className="flex grow flex-col gap-y-5 overflow-y-auto border-r border-[#c7d3e1] bg-[#d6dfea] px-6">
      <Logo />
      <SidebarGreeting />
      <nav className="flex flex-1 flex-col">
        <ul role="list" className="flex flex-1 flex-col gap-y-7">
          <li>
            <SidebarNavigation />
            <SidebarSpace />
            <SidebarPersonalNavigation />
          </li>
        </ul>
      </nav>
    </div>
  </div>
  )
}