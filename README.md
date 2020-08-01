# Web Developer @ gateB - Exercise Submission
This repository contains code for: 
* Java servlet backend which scrapes [Time.com's](http://time.com/) front pages of Tech, Business, and Sports to fetch news articles. Each article's title, excerpt, image url, page url, and category is then sent as a JSON response when a GET request is made to /news-items.
* Mongolia CMS and Vue.js frontend responsible for fetching the data from the backend and showcasing it in a grid of cards with a functioning filter. The filter allows certain news cards to appear based on their category and/or certain text in the title or excerpt of the news article. 

![Screenshot](https://i.imgur.com/SWhYSTl.png)

### Installation / Usage

**Preferred Method**
1. Obtain `apache-tomcat` and `light-modules` builds. There are two methods for this: docker or manual.
    #### Docker
    ```sh
    docker pull princes25/web_developer_gateb
    docker run -p 8080:8080 princes25/web_developer_gateb
    ```
    
    #### Manual
    Prerequisite: follow the "Install Java", "Install Node.js", and "Install Magnolia CLI" sections in this [tutorial](https://documentation.magnolia-cms.com/display/DOCS62/Install+Magnolia).
    - Download and Extract this [zip](https://drive.google.com/file/d/10oqfWcTxakTLIAM9aB1-TW5HHyXZGq3t/view?usp=sharing).
    - Run `mgnl start` in the directory where you extracted the zip contents (`apache-tomcat` and `light-modules`).
2. Go to `http://localhost:8080/magnoliaAuthor` once the server starts.
3. Sign in with username: `superuser` and password: `superuser`.
4. Go to `http://localhost:8080/magnoliaAuthor/Hello-Magnolia.html`.
5. Enjoy :)

**Backup Method**
1. Obtain Java servlet backend build.
    You can either download a zip [here](https://drive.google.com/file/d/1MFgrWpkISSzNzuhVgaHQP79z3McaLHJy/view?usp=sharing) or follow the instructions below to build from source:
    * Clone or download this repository 
    ```sh
    git clone https://github.com/PrinceS25/Web-Developer-gateB.git
    ```
    * Open [Eclipse IDE](https://www.eclipse.org/ide/) -> import from existing Maven project -> choose `pom.xml` in `servlet-json-backend` directory from this repository
    * Run with a Apache Tomcat server\
    **Note:** If you cannot add the project to the server, you may first have to go to the project settings -> project facets, convert to facet form, and enable "Dynamic Web Module"\
    **Note 2:** You may also need to export the .jar files to the lib, check the markers tab for "Classpath Dependency Validator Message -> Classpath entry org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER will not be exported or published. Runtime ClassNotFoundExceptions may result" message. Right click -> Quick Fix -> Mark the associated raw classpath entry as a publish/export dependency.
    * Obtain `servlet-json-backend` containing the build from your server directory
2. Follow [this](https://documentation.magnolia-cms.com/display/DOCS62/Install+Magnolia) tutorial to install Mongolia CLI
3. Place `servlet-json-backend` folder (obtained from step 1) inside `apache-tomcat/webapps` directory.\
    **Note:** Make sure `apache-tomcat` directory is the one generated in step 2
4. Follow [this](https://documentation.magnolia-cms.com/display/DOCS62/Hello+Magnolia+for+front-end+developers) tutorial until the beginning of "Tweak your light module" section **BUT** instead of using the hello-magnolia.zip they provide, use the `hello-magnolia` folder from the  `mongolia-module-frontend` directory in this repository or download the zip [here](https://drive.google.com/file/d/1zz-SInoBIHbWpPm2RgYYyq46p8Xd1E2A/view?usp=sharing).\
    **Note**: Before proceeding to the next step, you should have placed `hello-magnolia` inside the `light-modules` directory, logged in, created a new page, and added a component.\
    Your Magnolia jumpstart directory should look like this:
    ```
    <directory specified during jumpstart>/
    ├──apache-tomcat
    │   ├── ...
    │   ├── webapps
    │   │   ├── magnoliaAuthor
    │   │   └── servlet-json-backend
    │   │   └── ...
    │   └── ...
    ├── light-modules
    │   └── hello-magnolia
    |   |   ├── dialogs/
    |   |   │   └── ...
    |   |   ├── webresources/
    |   |   │   └── ...
    |   |   └── templates/
    |   |       ├── components/
    |   |       │   ├── newsItems.ftl
    |   |       │   ├── newsItems.yaml
    |   |       │   ├── quotation.ftl
    |   |       │   └── quotation.yaml
    |   |       └── pages/
    |   |           ├── hello.ftl
    |   |           └── hello.yaml
    |   └── ...
    └── ...
    ```
5. Add the News Items component.
6. Enjoy :)

### Known Bugs
* Time posting the same article to more than one category (i.e. two articles with same title) causes Vue to give "Duplicate keys" warning, sometimes preventing the app to update.
    - It's an easy fix.
    - Highly dependent on how Time categorizes their articles.

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
