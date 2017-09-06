module.exports = {
    entry: './src/main/webapp/js/index.js',

    output: {
        path:'/src/main/webapp/public/',
        filename: 'bundle.js'
    },

    devServer: {
        inline: true,
        port: 7777,
        contentBase:'/src/main/webapp/public/'
    },

    module: {
            loaders: [
                {
                    test: /\.(png|jpg|gif)$/,
                    loader: 'url-loader?limit=8192'
                },
                {
                    test: /\.js$/,
                    loader: 'babel-loader',
                    exclude: /node_modules/,
                    query: {
                        cacheDirectory: true,
                        presets: ['es2015', 'react']
                    }
                },
                {
                    test : /\.css$/,
                    loader: 'style-loader!css-loader'
                }
            ]
        }
};