
<#--  Place your DOM here  -->
<html>
    <head>
        <title>News Articles from Time.com</title>

        <#--  Vue.js CDN  -->
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

        <#--  Vuetify.js CDN  -->
        <script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    </head>
    
    <body>
        <div id="app">
            <v-app>
                <v-main>
                    <news-filter v-bind:news_items="newsItems" />
                </v-main>
            </v-app>
        </div>

        <#--  Local CSS styles  -->
        <style>
            .wrap-text {
                word-break: break-word;
                }
            .flex-center {
                display: flex;
                flex-direction: column;
                align-items: center;
                }
        </style>

        <#-- Place your custom scripts here  -->
        <script type = "text/javascript">

            /* 
             * Filter Component: Filters through news articles and sends them to children component for rendering
             * Props: news_items - JSON of news items fetched from Java servlet backend
             * Chilren Components: news-grid, news-item
            */
            Vue.component('news-filter', {
                props: ['news_items'],
                data: function() {
                    return {
                        tags: [
                            'Tech',
                            'Business',
                            'Sports',
                        ],

                        filters: {
                            selectedCats: ['All'],  // Default to All
                            text: '',
                        },

                        articles: this.news_items,  // Initially show all articles 
                    }
                },

                methods: {
                    // Filters through the news_items based on the type of filter (cat or text)
                    // and assigns them to the "articles" property to be then rendered in the template 
                    applyFilter(type) {
                        switch(type) {
                            // If called by filterByCategory()
                            case 'cat':
                                if (this.filters.selectedCats[0] === 'All') // Show all news items if "All" category is selected
                                    this.articles = this.news_items
                                else
                                    this.articles = this.news_items.filter( // Otherwise, show only news items with the selected category
                                        news_item => this.filters.selectedCats.some(
                                            category => category === news_item.category
                                        ))
                                break;
                            
                            // If called by filterByText()
                            case 'text':
                                break;
                        }
                    },

                    // Returns the concatenation of current filter and the new filter
                    updateFilters(newFilter) {
                        return Object.assign({}, this.filters, newFilter)
                    },

                    // 
                    filterByCategory(catVal) {
                        let namedCats = this.tags.filter((el, idx) => catVal.includes(idx))
                        this.filters = catVal.length > 0 ? this.updateFilters({selectedCats: namedCats}) : this.updateFilters({selectedCats: ['All']})
                        this.applyFilter('cat')
                    },

                    filterByText(text) {
                        this.filters = text.length > 0 ? this.updateFilters({text: text}) : this.updateFilters({text: ''})
                        this.applyFilter('text')
                    },
                },
                
                template:   `<v-container fluid>
                                <v-card class="mx-auto" max-width="400">
                                    <v-card-text class="pa-1">
                                        <h2 class="text-overline text-center">Filter by Category</h2>
                                        
                                        <v-chip-group multiple active-class="primary--text" class="flex-center" @change="filterByCategory">
                                            <v-chip filter outlined v-for="tag in tags" :key="tag">
                                                {{ tag }}
                                            </v-chip>
                                        </v-chip-group>
                                    </v-card-text>
                                </v-card>
                                <news-grid v-bind:articles="articles" />
                            </v-container>`
            });


            /* 
             * News Items Grid Component: Sets up a grid for children component to display cards on
             * Props: articles - news items sent from the filter (parent component)
             * Parent component: news-filter
             * Children component: news-item
            */
            Vue.component('news-grid', {
                props: ['articles'],
                template:   `<v-row dense>
                                <v-col v-for="article in articles" :key="article.title" :cols="article.flex">
                                    <news-item v-bind:article="article" v-bind:key="article.title" />
                                </v-col>
                            </v-row>`
            });


            /*
             * News Item Card Component: Renders a card for each news item
             * Props: article - news item received from the filter and grid (parents)
             * Parent components: news-filter, news-grid
            */
            Vue.component('news-item', {
                props: ['article'],
                data: () => ({
                    show: false,
                }),
                template: `<v-card class="mx-auto my-2" max-width="400" elevation=5 shaped>
                                <v-img class="white--text align-end" height="200px" v-bind:src="article.imgUrl" />
                                
                                <v-card-title class="wrap-text text-subtitle-2">{{article.title}}</v-card-title>
                                <v-card-subtitle class="py-0 text-caption">{{article.category}}</v-card-subtitle>

                                <v-card-actions>
                                    <v-btn color="orange" text v-bind:href="article.pageUrl" target="_blank">
                                        Read More
                                    </v-btn>

                                    <v-spacer></v-spacer>

                                    <v-btn text v-bind:disabled="show" @click="show = !show" x-small>
                                        Excerpt
                                    </v-btn>
                                    <v-btn icon @click="show = !show">
                                        <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                                    </v-btn>
                                </v-card-actions>

                                <v-expand-transition>
                                    <div v-show="show">
                                        <v-divider></v-divider>
                                        <v-card-text>{{article.excerpt}}</v-card-text>
                                    </div>
                                </v-expand-transition>
                            </v-card>`
            });


            // Intialize Vue once data has been fetched
            fetch("http://localhost:8080/servlet-json-backend/news-items").then(
                res => res.json()
            ).then(json => {
                var app = new Vue({
                    el: '#app',
                    data: { newsItems: json.newsItems },
                    vuetify: new Vuetify(),
                });
            })
        </script>
    </body>
</html>
