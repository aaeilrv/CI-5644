"use client";

import { useState } from "react";
import { BookOpenIcon } from "@heroicons/react/16/solid";
import { Bars3Icon } from "@heroicons/react/16/solid";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { useUser } from "@auth0/nextjs-auth0/client";

interface NavItems {
  href: string;
  text: string;
}

interface NavbarProps {
  navigation_links: NavItems[];
}

const Navbar: React.FC<NavbarProps> = ({ navigation_links }) => {
  const [open, setOpen] = useState(false);
  const { user, isLoading } = useUser();
  const pathName = usePathname();

  return (
    <nav className="bg-transparent fixed w-full z-30 top-0 start-0">
      <div className="px-10 py-2 bg-white bg-opacity-50">
          <div className="grid grid-cols-2 md:grid-cols-3 place-content-between py-2 items-center">
            {/* Title */}
            <Link href="/" className="flex">
              <BookOpenIcon className="w-8" />
              <div className="font-bold text-2xl">Barajitas</div>
            </Link>
            {
              <div className="hidden md:flex justify-center space-x-5 font-medium">
                {navigation_links.map((link) => (
                  <Link href={link.href} key={link.href} className="mr-4">
                    {link.text}
                  </Link>
                ))}
              </div>
            }

            {/*Login / Logout */}
            <div>
              {
                user ? (
                  <div className="flex justify-end">
                    <Link
                      href="/api/auth/logout"
                      className="hidden md:flex font-bold text-xl"
                    >
                      Logout
                    </Link>
                  </div>
                ) : (
                  <div className="flex justify-end">
                    <Link
                      href="/api/auth/login"
                      className="hidden md:flex font-bold text-xl"
                    >
                      Login
                    </Link>
                  </div>
                )
              }

              {/* Mobile */}
              <div className="flex justify-end">
                <button onClick={() => setOpen(!open)}>
                  <Bars3Icon className="md:hidden md: w-8" />
                </button>
              </div>
            </div>
          </div>
      </div>

      {/* Mobile */}
      { open && (
        <div className="flex justify-end p-2 md:hidden">
          <div className="bg-white px-2 py-2 bg-opacity-50 rounded-xl text-right">
            {
              navigation_links.map((link) => (
                <Link href={link.href} key={link.href} className="block">
                  {link.text}
                </Link>
              ))
            }
            {user ? (
              <Link
                href="/api/auth/logout"
                className="block py-2 font-bold text-xl px-4 rounded-xl hover:bg-white hover:bg-opacity-50"
                >
                Logout
              </Link>
            ) : (
              <Link
                href="/landing"
                className="block py-2 font-bold text-xl px-4 rounded-xl hover:bg-white hover:bg-opacity-50"
              >
                Entrar
              </Link>
            )}
          </div>
        </div>
      )}
    </nav>
  );
}

export default Navbar;