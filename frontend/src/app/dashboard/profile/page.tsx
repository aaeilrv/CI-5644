"use client"
import { getJwtToken } from "@/app/helpers/getJwtToken";
import { getAccessToken } from "@auth0/nextjs-auth0";
import { useUser } from "@auth0/nextjs-auth0/client";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function Profile() {
  const {user, isLoading} = useUser()
  const token = sessionStorage.getItem('token')


  if (isLoading) return <div>Loading...</div>

  return (
    <div>
      Probando el sistema de routing nuevo de nextjs. Esto es el profile (?)
      
    </div>
  )
}