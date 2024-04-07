import React, { useState } from 'react';

const SecondFilter = ({currentFilter, setCurrentFilter}: {currentFilter: number, setCurrentFilter: React.Dispatch<React.SetStateAction<number>>}) => {

  const [filters, setFilters] = useState([
    {
      id: 0,
      name: "Recibidas",
      active: true
    },
    {
      id: 1,
      name: "Creadas por mi",
      active: false
    }
  ])

  const handleButtonClick = (filterId: number) => {
    setFilters(prevFilters =>
      prevFilters.map(filter =>
        filter.id === filterId ? { ...filter, active: true } : { ...filter, active: false }
      )
    );
    setCurrentFilter(filterId);
  };

  return (
    <>
      <div className='space-x-4'>
        {
          filters.map((filter) => (
            <button
              key={filter.id}
              className={`inline-flex items-center rounded-md ${filter.active ? 'bg-slate-400' : ''} px-2 py-1 text-xs font-medium text-black ring-1 ring-inset bg-ring-300`}
              onClick={() =>
                handleButtonClick(filter.id)
              }
            >
              {filter.name}
            </button>
          ))
        }
      </div>
    </>
  );
};

export default SecondFilter;
