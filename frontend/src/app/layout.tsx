import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import '../styles/globals.css'
import React from 'react';
import { UserProvider } from '@auth0/nextjs-auth0/client';

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Barajitas',
  description: 'Intercambia barajitas',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        {children}
      </body>

      <UserProvider>
       <body>{children}</body>
      </UserProvider>
    </html>
  )
}
