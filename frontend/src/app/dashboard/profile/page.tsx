"use client"
import { useUser } from "@auth0/nextjs-auth0/client";
import Link from "next/link";

export default function Profile() {
  const {user, isLoading} = useUser()

  if (isLoading) return <div>Loading...</div>

  return (
    <div>
      Probando el sistema de routing nuevo de nextjs. Esto es el profile (?)
    </div>
  )
}