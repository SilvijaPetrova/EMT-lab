import './App.css';
import React, {Component} from "react";
import Repository from "../../repository/repository";
import Books from "../Books/BookList";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd";
import BookEdit from "../Books/BookEdit";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            countries: [],
            categories: [],
            authors: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/books/add"} exact render={() =>
                            <BookAdd categories={this.state.categories}
                                        authors={this.state.authors}
                                        onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit categories={this.state.categories}
                                         authors={this.state.authors}
                                         onEditBook={this.editBook}
                                         book={this.state.selectedBook}/>}/>
                        <Route path={"/books"} exact render={() =>
                            <Books books={this.state.books}
                                      onDelete={this.deleteBook}
                                      onEdit={this.getBook}/>}/>
                        <Redirect to={"/books"}/>
                    </div>
                </main>
            </Router>

        );
    }

    componentDidMount() {
        this.loadCountries();
        this.loadAuthors();
        this.loadBooks();
        this.loadCategories();
    }

    loadCategories = () => {
        this.setState({
            categories: [
                "NOVEL",
                "THRILER",
                "HISTORY",
                "FANTASY",
                "BIOGRAPHY",
                "CLASSICS",
                "DRAMA"
            ]
        });
    }

    loadCountries = () => {
        Repository.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }

    loadBooks = () => {
        Repository.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadAuthors = () => {
        Repository.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    deleteBook = (id) => {
        Repository.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, author, category, availableCopies) => {
        Repository.addBook(name, author, category, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        Repository.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availableCopies) => {
        Repository.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
}

export default App;
