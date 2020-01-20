export class BlogPost {
    status: string[];
    _id: string;
    content: string;
    title: string;
    shortdescription : string;
    author: string;
    Created_date: string;

    //assign vals from json to properties
    constructor(values: Object = {}) { 
        Object.assign(this, values);
    }
}
