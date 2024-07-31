import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { setToken } from "../services/localStorageService";
import { Box, CircularProgress, Typography } from "@mui/material";

export default function Authenticate() {
  const navigate = useNavigate();
  const [isLoggedin, setIsLoggedin] = useState(false);

  useEffect(() => {
    console.log(window.location.href);

    const authCodeRegex = /code=([^&]+)/;
    const isMatch = window.location.href.match(authCodeRegex);


    if (isMatch) {
      const authCode = isMatch[1];
      debugger
      fetch(
        `http://localhost:8888/socialapp/login/outbound/authentication?code=${authCode}`,
        {
          method: "POST",
        }
    
      )
    
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          setToken(data.result?.token);
          setIsLoggedin(true);
        })
        .catch((error) => {
          console.error("Error during authentication:", error);
        });
        debugger
    }
  }, []);

  useEffect(() => {
    if (isLoggedin) {
      navigate("/");
    }
  }, [isLoggedin, navigate]);

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: "30px",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      <CircularProgress />
      <Typography>Authenticating...</Typography>
    </Box>
  );
}
