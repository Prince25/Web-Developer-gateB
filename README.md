# Web Developer @ gateB - Exercise Submission
This repository contains code for: 
* Java servlet backend which scrapes [Time.com's](http://time.com/) front pages of Tech, Business, and Sports to fetch news articles. Each article's title, excerpt, image url, page url, and category is then sent as a JSON response when a GET request is made to /news-items.
* Mongolia CMS and Vue.js frontend responsible for fetching the data from the backend and showcasing it in a grid of cards with a functioning filter. The filter allows certain news cards to appear based on their category and/or certain text in the title or excerpt of the news article. 

### Installation / Usage
1. Obtain Java servlet backend build.
    You can either download a zip [here](https://drive.google.com/file/d/1MFgrWpkISSzNzuhVgaHQP79z3McaLHJy/view?usp=sharing) or follow the instructions below to build from source:
    * Clone or download this repository 
    ```git clone https://github.com/PrinceS25/Web-Developer-gateB.git```
    * Open [Eclipse IDE](https://www.eclipse.org/ide/) -> import from existing Maven project -> choose ```pom.xml``` in ```servlet-json-backend``` directory from this repository
    * Run with a Apache Tomcat server
    * Obtain ```servlet-json-backend``` containing the build from your server directory
2. Follow [this](https://documentation.magnolia-cms.com/display/DOCS62/Install+Magnolia) tutorial to install Mongolia CLI
3. Place ```servlet-json-backend``` folder (obtained from step 1) inside ```apache-tomcat/webapps``` directory.\
    **Note:** Make sure ```apache-tomcat``` directory is the one generated in step 2
4. Follow [this](https://documentation.magnolia-cms.com/display/DOCS62/Hello+Magnolia+for+front-end+developers) tutorial until the beginning of "Tweak your light module" section **BUT** instead of using the hello-mangolia.zip they provide, use the ```hello-mangolia``` folder from the  ```mongolia-module-frontend``` directory in this repository or download the zip [here](https://drive.google.com/file/d/1zz-SInoBIHbWpPm2RgYYyq46p8Xd1E2A/view?usp=sharing).\
    **Note**: Before proceeding to the next step, you should have placed ```hello-mangolia``` inside the ```light-modules``` directory, logged in, created a new page, and added a component.
5. Add the News Items component.
6. Enjoy :)

### Technologies
* [Magnolia CMS](https://www.magnolia-cms.com/)
* [Apache Tomcat](http://tomcat.apache.org/)
* [Apache Maven](https://maven.apache.org/)
* [Vue.js](https://vuejs.org/)
* [Vuetify.js](https://vuetifyjs.com/en/)
* [Material Design Icons](https://materialdesignicons.com/)

### Maven Dependencies for Java Backend
* [javax.servlet-api v4.0.1](https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api)
* [gson v2.8.6](https://mvnrepository.com/artifact/com.google.code.gson/gson)
* [jsoup v1.13.1](https://mvnrepository.com/artifact/org.jsoup/jsoup)

### Resources
* [Jakarta Servlet on wikipedia](https://en.wikipedia.org/wiki/Jakarta_Servlet)
* [How to Return a JSON Response from a Java Servlet](https://www.javaguides.net/2019/05/how-to-return-json-response-from-java-servlet.html)
* [jsoup: Java HTML Parser](https://jsoup.org/)
* [Chrome DevTools](https://developers.google.com/web/tools/chrome-devtools)
* [Install Magnolia](https://documentation.magnolia-cms.com/display/DOCS62/Install+Magnolia#InstallMagnolia-InstallJava)
* [Hello Magnolia for front-end developers](https://documentation.magnolia-cms.com/display/DOCS62/Hello+Magnolia+for+front-end+developers)
* [Achieve multiple filtering in the Data table component <v-data-table>](https://front.id/en/articles/vuetify-achieve-multiple-filtering-data-table-component)
* [VueJS prop is undefined when read in data() method](https://stackoverflow.com/questions/50086765/vuejs-prop-is-undefined-when-read-in-data-method)
