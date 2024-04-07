import React, { Component } from 'react';
import Link from "next/link";
import { BookOpenIcon } from '@heroicons/react/24/outline'

export default function Logo() {
  return (
    <div className="flex h-16 shrink-0 items-center">
      <Link href="/" className="flex">
        <BookOpenIcon className="w-8" />
        <div className="font-bold text-2xl">Barajitas</div>
      </Link>
    </div>
  )
}