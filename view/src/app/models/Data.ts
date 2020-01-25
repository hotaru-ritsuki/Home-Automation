export class Data<T> {
  constructor(
    public content: [T],
    public totalPages: number,
    public totalElements: number
  ) {
  }
}
