import { Injectable } from "@angular/core";
import { HttpHeaders } from "@angular/common/http";
import { map, catchError } from "rxjs/operators";
import { ApiService } from "./api.service";
import { environment } from "../../environments/environment";
import { BlogPost } from "../models/blog-post";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class BlogPostService {
  constructor(private apiService: ApiService) {}

  public GetPosts(): Observable<BlogPost[]> {
    return this.apiService.Get(environment.api.entries+"/posts").pipe(
      map(json => {
        return json.map(post => new BlogPost(post));
      })
    );
  }

  public GetPost(id: string): Observable<BlogPost> {
    return this.apiService.Get(environment.api.entries + "/posts/" + id).pipe(
      map(json => {
        return new BlogPost(json);
      })
    );
  }

  public GetPostBytag(tag: string): Observable<BlogPost[]> {
    return this.apiService.Get(environment.api.entries + "/post/" + tag).pipe(
      map(json => {
        return json.map(post => new BlogPost(post));
      })
    );
  }

  public CreatePost(post: BlogPost): Observable<any> {
    return this.apiService.Post(environment.api.entries + "/posts", post);
  }

  public deletePost(id) {
    return this.apiService.Delete(environment.api.entries + "/posts/" + id);
  }
}
