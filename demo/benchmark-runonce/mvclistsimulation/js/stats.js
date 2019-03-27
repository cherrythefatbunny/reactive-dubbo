var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "75000",
        "ok": "75000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "203",
        "ok": "203",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2151",
        "ok": "2151",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1186",
        "ok": "1186",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "597",
        "ok": "597",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1513",
        "ok": "1513",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1658",
        "ok": "1659",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1825",
        "ok": "1825",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2019",
        "ok": "2019",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 21809,
        "percentage": 29
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 8755,
        "percentage": 12
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 44436,
        "percentage": 59
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "669.643",
        "ok": "669.643",
        "ko": "-"
    }
},
contents: {
"req_mvclist-dde7f": {
        type: "REQUEST",
        name: "mvclist",
path: "mvclist",
pathFormatted: "req_mvclist-dde7f",
stats: {
    "name": "mvclist",
    "numberOfRequests": {
        "total": "75000",
        "ok": "75000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "203",
        "ok": "203",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2151",
        "ok": "2151",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1186",
        "ok": "1186",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "597",
        "ok": "597",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1513",
        "ok": "1513",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1658",
        "ok": "1658",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1825",
        "ok": "1825",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2019",
        "ok": "2019",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 21809,
        "percentage": 29
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 8755,
        "percentage": 12
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 44436,
        "percentage": 59
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "669.643",
        "ok": "669.643",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
