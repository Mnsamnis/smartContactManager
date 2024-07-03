console.log("hello")
let currentTheme = getTheme();

// Initial theme setup
document.addEventListener("DOMContentLoaded", () => {
  changeTheme();
});

function changeTheme() {
  // Apply the current theme to the webpage
  changePageTheme(currentTheme, "");

  // Set up the event listener for the theme change button
  const changeThemeButton = document.querySelector("#theme_change_button");
  const themeIcon = document.querySelector("#theme_icon");
  const lightPath = "M17.8 13.75a1 1 0 0 0-.859-.5A7.488 7.488 0 0 1 10.52 2a1 1 0 0 0 0-.969A1.035 1.035 0 0 0 9.687.5h-.113a9.5 9.5 0 1 0 8.222 14.247 1 1 0 0 0 .004-.997Z";
  const darkPath = "M10 15a5 5 0 1 0 0-10 5 5 0 0 0 0 10Zm0-11a1 1 0 0 0 1-1V1a1 1 0 0 0-2 0v2a1 1 0 0 0 1 1Zm0 12a1 1 0 0 0-1 1v2a1 1 0 1 0 2 0v-2a1 1 0 0 0-1-1ZM4.343 5.757a1 1 0 0 0 1.414-1.414L4.343 2.929a1 1 0 0 0-1.414 1.414l1.414 1.414Zm11.314 8.486a1 1 0 0 0-1.414 1.414l1.414 1.414a1 1 0 0 0 1.414-1.414l-1.414-1.414ZM4 10a1 1 0 0 0-1-1H1a1 1 0 0 0 0 2h2a1 1 0 0 0 1-1Zm15-1h-2a1 1 0 1 0 0 2h2a1 1 0 0 0 0-2ZM4.343 14.243l-1.414 1.414a1 1 0 1 0 1.414 1.414l1.414-1.414a1 1 0 0 0-1.414-1.414ZM14.95 6.05a1 1 0 0 0 .707-.293l1.414-1.414a1 1 0 1 0-1.414-1.414l-1.414 1.414a1 1 0 0 0 .707 1.707Z";

  // Set initial SVG path based on current theme
  themeIcon.setAttribute("d", currentTheme === "light" ? lightPath : darkPath);

  changeThemeButton.addEventListener("click", () => {
    let oldTheme = currentTheme;
    console.log("change theme button clicked");

    // Toggle the theme
    if (currentTheme === "dark") {
      currentTheme = "light";
      themeIcon.setAttribute("d", lightPath); // Set to light theme path
    } else {
      currentTheme = "dark";
      themeIcon.setAttribute("d", darkPath); // Set to dark theme path
    }

    console.log(currentTheme);
    // Apply the new theme to the webpage
    changePageTheme(currentTheme, oldTheme);
  });
}

// Store the theme in localStorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// Retrieve the theme from localStorage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

// Apply the theme to the page
function changePageTheme(theme, oldTheme) {
  // Update the theme in localStorage
  setTheme(currentTheme);

  // Remove the old theme class from the <html> element
  if (oldTheme) {
    document.querySelector("html").classList.remove(oldTheme);
  }

  // Add the new theme class to the <html> element
  document.querySelector("html").classList.add(theme);
}


//   // change the text of button
//   document
//     .querySelector("#theme_change_button")
//     .querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
