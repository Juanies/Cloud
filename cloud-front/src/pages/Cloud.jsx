import  { useState, useEffect, useCallback } from 'react';
import {useDropzone} from 'react-dropzone'

function Cloud() {
  const [files, setFiles] = useState([]);

  const onDrop = useCallback((acceptedFiles) => {
    console.log(acceptedFiles);
  }, []);

  const { getRootProps, getInputProps, isDragActive, acceptedFiles } =
    useDropzone({ onDrop });

    const handleSubmit = async (e) => {
      e.preventDefault();

      const formData = new FormData();
      acceptedFiles.forEach((file) => {
        formData.append('files', file, file.name); // Usar 'files' como el nombre de los archivos
      });

      const response = await fetch("http://localhost:8080/api/files/upload", {
        method: "POST",
        credentials: 'include',
        body: formData,
      });

      const data = await response.text();
      console.log(data);

      setFiles([]);
    };

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
      <div className='h-[100dvh]'>
        <form onSubmit={handleSubmit}>
          <input type="text" />

          <div className='visibility: hidden;
'
            {...getRootProps()}
          >
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
          </div>

          {acceptedFiles[0] && (
            <img src={URL.createObjectURL(acceptedFiles[0])} alt=""
              style={{
                width: '300px',
                height: '300px'
              }}
            />
          )}

          <button>Enviar</button>
        </form>


      </div>

    );
  }


export default Cloud;
