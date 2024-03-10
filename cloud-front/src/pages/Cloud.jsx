import  { useState, useEffect } from 'react';

function Cloud() {
  const [files, setFiles] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/files/getfiles", {
      method: "GET",
      credentials: 'include'
    })
      .then((response) => response.json())
      .then((data) => {
        setFiles(data);
      })
      .catch((error) => console.log(error));
  }, []);


  //#333333
    //#262626

  return (
    <div className='flex flex-col w-[100dvw]   bg-[#333] justify-center'>
      <h2 className="text-2xl ">Archivos:</h2>
      <ul className='flex flex-col'>
        {files.map((file, index) => (
          <li key={index} className='flex  bg-[#262626]'>
            <p> {file.filename}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Cloud;
