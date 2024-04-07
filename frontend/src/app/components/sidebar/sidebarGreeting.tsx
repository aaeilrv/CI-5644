import { useUser } from "@auth0/nextjs-auth0/client";

export default function SidebarGreeting() {
  const { user} = useUser();
  return (
  <div className='w-full flex justify-center'>
    <h1 className='font-bold text-2xl'>¡Hola, {user?.nickname}!</h1>
  </div>
  )
}