'use client';
import '../../styles/globals.css'
import React  from 'react';
import Sidebar from '../components/sidebar/sidebar';

export default function LoggedInLayout({ children }: {
  children: React.ReactNode
}) {
  return (
    <>
      <div>
        <Sidebar />
        <main className="py-10 lg:pl-72">
          <div className="px-4 sm:px-6 lg:px-8">{children}</div>
        </main>
      </div>
    </>
  )
}