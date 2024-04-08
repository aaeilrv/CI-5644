import { usePathname } from 'next/navigation';
import {
  BookOpenIcon,
  UsersIcon,
  ArrowPathIcon,
  CreditCardIcon
} from '@heroicons/react/24/outline'

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(' ')
}

export default function SidebarNavigation() {
  const pathname = usePathname()

  const navigation = [
    { name: 'Mi álbum', href: '/album', icon: BookOpenIcon, current: pathname === '/album' },
    { name: 'Intercambio', href: '/exchange', icon: ArrowPathIcon, current: pathname === '/exchange' },
    { name: 'Comprar barajitas', href: '/buy', icon: CreditCardIcon, current: pathname === '/buy' },
    { name: 'Leaderboard', href: '/leaderboard', icon: UsersIcon, current: pathname === '/leaderboard' },
  ]
  
  return (
    <ul role="list" className="-mx-2 space-y-1">
      {navigation.map((item) => (
        <li key={item.name}>
          <a
          data-testid={item.name}
            href={item.href}
            className={classNames(
              item.current
                ? 'bg-gray-50 text-indigo-600'
                : 'text-gray-700 hover:text-indigo-600 hover:bg-gray-50',
              'group flex gap-x-3 rounded-md p-2 text-sm leading-6 font-semibold'
            )}
          >
            <item.icon
              className={classNames(
                item.current ? 'text-indigo-600' : 'text-gray-700 group-hover:text-indigo-600',
                'h-6 w-6 shrink-0'
              )}
              aria-hidden="true"
            />
            {item.name}
          </a>
        </li>
      ))}
    </ul>
  )
}