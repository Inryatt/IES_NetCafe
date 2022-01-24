import React, { useState, useEffect } from "react";
import { Nav, Pagination } from "react-bootstrap";
import AlertNotif from "../../../components/AlertNotif/AlertNotif"


const PagedAlerts = ({alerts, curPage, totalPages, dismissHandler = null, changePageHandler}) => {

    // pass correct number to handler function
    const handlePage = (e) => {
        let val
        if (e.target.attributes.value == undefined)
            val = e.target.parentElement.attributes.value.value;
        else
            val = e.target.attributes.value.value;

        if (val == "prev")
            changePageHandler(curPage-1);
        else if (val == "next")
            changePageHandler(curPage/1 +1);
        else
            changePageHandler(val);
    }

    // create each numbered page button
    const [pages, setPages] = useState([]);


    useEffect(() => {
        console.log("total pages: " + totalPages)

        const pagesArray = [];
        if (totalPages<5) {
            for (let i = 1; i <= totalPages; i++) {
                pagesArray.push(i);
            }
        } else {
            // 1 2 3 ...  X
            if (curPage<=2) {
                for (let i=1; i<=3; i++) {
                    pagesArray.push(i);
                }
                pagesArray.push(-1);
                pagesArray.push(totalPages);
            // 1 ...  X-2 X-1 X
            } else if (curPage>=totalPages-1) {
                pagesArray.push(1);
                pagesArray.push(-1);
                for (let i=totalPages-2; i<=totalPages; i++) {
                    pagesArray.push(i);
                }
            // ... X-1 X X+1 ...
            } else {
                let currentpage = parseInt(curPage);
                pagesArray.push(-1);
                pagesArray.push(currentpage-1);
                pagesArray.push(currentpage);
                pagesArray.push(currentpage+1);
                pagesArray.push(-1);
            }
        }
        setPages(pagesArray);
    }, [totalPages, curPage]);


    return (
        alerts.length > 0 ?
        <>
            <Nav>
                <Pagination className="mx-auto mx-lg-0 ml-lg-auto mr-0">
                    <Pagination.First onClick={handlePage} value={1} disabled={curPage == 1} />
                    <Pagination.Prev  onClick={handlePage} value="prev" disabled={curPage == 1} />

                    {
                    // Not sure how to render this yet, so keeping the condition false for now
                    false &&
                    <Pagination.Ellipsis />
                    }
                    
                    {
                        pages.map(
                            page =>
                            page>=1 
                            ?
                            <Pagination.Item onClick={handlePage} active={page == curPage} value={page} key={page}>
                                {page}
                            </Pagination.Item>
                            :
                            <Pagination.Ellipsis />
                        )
                    }

                    {
                    // Not sure how to render this yet, so keeping the condition false for now
                    false &&
                    <Pagination.Ellipsis />
                    }

                    <Pagination.Next onClick={handlePage} value="next" disabled={curPage == totalPages}/>
                    <Pagination.Last onClick={handlePage} value={totalPages} disabled={curPage == totalPages}/>
                </Pagination>
            </Nav>

            {alerts.map(alert => <AlertNotif alert={alert} dismissHandler={dismissHandler} />)}
        </>
        :
        <p>No old alerts</p>
    )
}

export default PagedAlerts;