import { usePathname } from 'next/navigation';
import {
  UserIcon,
  ArrowLeftStartOnRectangleIcon
} from '@heroicons/react/24/outline'

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(' ')
}

export default function SidebarPersonalNavigation() {
  const pathname = usePathname()

  const personal_navigation = [
    { name: 'Perfil', href: '/profile', icon: UserIcon, current: pathname === '/profile' },
    { name: 'Cerrar Sesión', href: '/api/auth/logout', icon: ArrowLeftStartOnRectangleIcon, current: false },
  ]
  
  return (
    <ul role="list" className="-mx-2 space-y-1 py-10">
      {personal_navigation.map((item) => (
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